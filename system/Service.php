<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 4:56 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system;

use wyzetech\shabisa\system\service\ServiceContext;
use wyzetech\shabisa\system\service\ServiceLocator;

abstract class Service
{
    private $serviceLocator = null;

    protected function __construct(ServiceContext $serviceContext = null)
    {
        if (isset($serviceContext) === true) {
            $this->serviceLocator = $serviceContext->getServiceLocator();
        }
    }

    public function find(string $name): Service
    {
        return $this->serviceLocator->find($name);
    }

    public function register(string $name, Service $service): void
    {
        echo "<br />Called Register!!!<br />";
        $this->serviceLocator->registry()->register($name, $service);
    }

    public function setServiceLocator(ServiceLocator $serviceLocator)
    {
        $this->serviceLocator = $serviceLocator;
    }

    public abstract function execute();
}
