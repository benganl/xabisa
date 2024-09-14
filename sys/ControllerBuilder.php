<?php

declare(strict_types=1);

namespace xabisa\sys;

class ControllerBuilder
{
    public static function build(Request $request): Controller
    {
        $controllerClass = $request->controller;

        
        $controllerObj = new $controllerClass();
        
        return $controllerObj;
    }
}
