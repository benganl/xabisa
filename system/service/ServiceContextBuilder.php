<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 8:36 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\service;

class ServiceContextBuilder
{
    private $serviceLocator;

    function __construct()
    {
    }

    public function getServiceLocator(): ServiceLocator
    {
        return $this->serviceLocator;
    }

    public function withServiceLocator(ServiceLocator $serviceLocator): ServiceContextBuilder
    {
        $this->serviceLocator = $serviceLocator;
        return $this;
    }

    public function build(): ServiceContext
    {
        return new ServiceContext($this);
    }
}
