<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\context;

use wyzetech\shabisa\system\Configuration;
use wyzetech\shabisa\system\context\RequestContext;
use wyzetech\shabisa\system\service\ServiceRegistry;

class ApplicationContext
{
    public static function handle(Configuration $config, ServiceRegistry $serviceRegistry, array $request)
    {
        $requestContext = new RequestContext($config, $serviceRegistry, $request);
        $requestContext->service();
    }
}
