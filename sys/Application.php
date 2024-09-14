<?php

declare(strict_types=1);

namespace xabisa\sys;

class Application
{

    public function run(?Request &$request)
    {
        
        $config = ConfigBuilder::getInstance()->build();
        $controllerObj = ControllerBuilder::build($request);

        $executionContext = new ExecutionContext($config); // provide db, service, log access. Basically all the non-functionals.

        $controllerHandler = new ControllerHandler($executionContext, $request); // mainly for security and transactions

        $controllerHandler->handle($controllerObj);
    }
}
