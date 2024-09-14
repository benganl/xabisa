<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;
use wyzetech\shabisa\system\context\ApplicationContext;
use wyzetech\shabisa\system\service\ServiceRegistry;

final class Application
{
    public static function bootstrap(array $request): void
    {
        $config = Configuration::getInstance($request);
        $serviceRegistry = ServiceRegistry::getInstance($request, $config);
        ApplicationContext::handle($config, $serviceRegistry, $request);
    }
}
