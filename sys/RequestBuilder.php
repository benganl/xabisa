<?php

declare(strict_types=1);

namespace xabisa\sys;

use xabisa\utils\Sanitizer;

class RequestBuilder
{
    public static function build(): Request
    {
        $uri = trim(parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH), '/');
        $segments = explode('/', $uri);

        $module = 'core';
        $controllerName = 'IndexController';
        $methodName = 'index';
        $params = [];

        $requestType = self::getRequestType();

        if (!empty($segments[0])) {
            $module = strtolower($segments[0]);
            if (count($segments) === 1) {
                $controllerName = 'IndexController';
            } elseif (count($segments) === 2) {
                $controllerName = ucfirst(trim($segments[1])) . 'Controller';
            }
        }

        if ($requestType === 'json') {
            $raw_json_data = file_get_contents("php://input");
            $decoded_data = json_decode($raw_json_data, true);

            if (json_last_error() === JSON_ERROR_NONE) {
                $params += Sanitizer::sanitize($decoded_data);
            } else {
                echo "Invalid JSON format!<br />";
                return [];
            }
        } elseif (!empty($_REQUEST)) {
            $params += Sanitizer::sanitize($_REQUEST);
            $methodName = isset($params['m']) ? $params['m'] : array_key_first($params);
        }

        $controllerClass = "xabisa\\app\\$module\\controller\\$controllerName";

        $request = new Request();
        $request->controller = $controllerClass;
        $request->method = $methodName;
        $request->module = $module;
        $request->params = $params;
        $request->isPost = ('post' === $requestType || 'form-urlencoded' === $requestType) ? true : false;
        
        foreach ($_SERVER as $key => $value) {
            $request->$key = $value;
        }

        foreach ($_REQUEST as $key => $value) {
            $request->$key = $value;
        }

        return $request;
    }

    private static function getRequestType(): string
    {
        $requestMethod = $_SERVER['REQUEST_METHOD'] ?? '';
        $body = null;
        if ($requestMethod === 'POST') {
            $contentType = $_SERVER['CONTENT_TYPE'] ?? '';
            if (strpos($contentType, 'application/json') !== false) {
                $body = 'json';
            } elseif (strpos($contentType, 'application/x-www-form-urlencoded') !== false) {
                $body = 'form-urlencoded';
            } elseif (strpos($contentType, 'multipart/form-data') !== false) {
                $body = 'multipart';
            } else {
                $body = 'post';
            }
        } elseif ($requestMethod === 'GET') {
            $body = 'get';
        } elseif ($requestMethod === 'PUT') {
            $body = 'put';
        } elseif ($requestMethod === 'DELETE') {
            $body = 'delete';
        } else {
            throw new \Exception('Could not understand the request. Unknown request method!');
        }
        return $body;
    }
}
