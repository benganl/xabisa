<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 8:32 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\service;

class ServiceContext
{
    private $serviceLocator = null;

    function __construct(ServiceContextBuilder $builder)
    {
        $this->serviceLocator = $builder->getServiceLocator();
    }

    public static function builder()
    {
        return new ServiceContextBuilder();
    }

    public function getServiceRegistry(): ServiceRegistry
    {
        return $this->serviceLocator->registry();
    }

    public function getServiceLocator(): ServiceLocator
    {
        return $this->serviceLocator;
    }
}
