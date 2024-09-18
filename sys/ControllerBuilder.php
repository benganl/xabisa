<?php

declare(strict_types=1);

namespace xabisa\sys;

class ControllerBuilder
{
    public static function build(Request &$request): Controller
    {
        $controllerClass = $request->controller;
        var_dump($controllerClass);
        if (!class_exists($controllerClass) || !is_subclass_of($controllerClass, Controller::class)) {
            throw new InvalidArgumentException("Invalid controller class: $controllerClass");
        }
        $controllerObj = new $controllerClass();
        $controllerObj->setRequest($request);
        $controllerObj->setView(new View());
        return $controllerObj;
    }
}
