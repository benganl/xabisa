<?php

declare(strict_types=1);

namespace xabisa\sys;

class Application
{
    public function run(?Request &$request): void
    {
        $config = Config::getInstance();
        $controllerObj = ControllerBuilder::build($request);
        
        $executionContext = new ExecutionContext($config); // provide db, service, log access. Basically all the non-functionals.

        $controllerHandler = new ControllerHandler($executionContext); // mainly for security and transactions
        $controllerHandler->handle($controllerObj);
    }
}
