<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\context;

use wyzetech\shabisa\system\Configuration;
use wyzetech\shabisa\system\ControllerFactory;
use wyzetech\shabisa\system\service\ServiceRegistry;

class RequestContext
{
    private ServiceRegistry $serviceRegistry;
    private Configuration $config;
    private array $request;

    public function __construct(Configuration $config, ServiceRegistry $serviceRegistry, array $request)
    {
        $this->config = $config;
        $this->serviceRegistry = $serviceRegistry;
        $this->request = $request;
    }

    public function service(): void
    {
        $methodName = $this->request["method"];
        $controllerObj = ControllerFactory::build($this->request, $this->serviceRegistry, $this->config);
        \call_user_func([$controllerObj, $methodName], $this->request);
    }
}
