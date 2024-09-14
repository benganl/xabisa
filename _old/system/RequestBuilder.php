<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;
use wyzetech\shabisa\system\utils\Sanitizer;

class RequestBuilder
{
    public function __construct()
    {
        // my constructor
    }

    public static function build(): array
    {
        $uri = trim(parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH), '/');
        $segments = explode('/', $uri);

        $module = 'core';
        $controllerName = 'IndexController';
        $methodName = 'index';
        $params = [];

        $requestType = RequestBuilder::getRequestType();

        if (!empty($segments[0])) {
            $module = strtolower($segments[0]);
            if (count($segments) === 1) {
                $controllerName = 'IndexController';
            } elseif (count($segments) === 2) {
                $controllerName = ucfirst(trim($segments[1])) . 'Controller';
            } elseif (count($segments) === 3) {
                $methodName = Sanitizer::sanitize(trim($segments[2]));
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
        }

        $controllerClass = "wyzetech\\shabisa\\application\\$module\\controller\\$controllerName";

        $request = [];
        $request["controller"] = $controllerClass;
        $request["method"] = $methodName;
        $request["module"] = $module;
        $request["params"] = $params;
        $request["isPost"] = ('post' === $requestType || 'form-urlencoded' === $requestType) ? true : false;
        return array_merge($_SERVER, $_REQUEST, $request);
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
