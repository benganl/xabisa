<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;
use wyzetech\shabisa\system\context\ExecutionContext;
use wyzetech\shabisa\system\service\ServiceRegistry;

class ControllerFactory
{
    public static function build(array $request, ServiceRegistry $serviceRegistry, Configuration &$configuration): Controller
    {
        $controllerClass = $request['controller'];
        $controllerObj = new $controllerClass();
        $controllerObj->setExecutionContext(new ExecutionContext($request, $serviceRegistry, $configuration));
        return $controllerObj;
    }
}
