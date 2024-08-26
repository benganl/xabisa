<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 5:09 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\service;

use wyzetech\shabisa\system\Service;

class ServiceLocator extends BaseServiceLocator
{
    private static $instance = null;
    private $registry = null;

    private function __construct(ServiceRegistry $registry)
    {
        $this->registry = $registry;
    }

    public static function getInstance(ServiceRegistry $registry): ServiceLocator
    {
        if (null === self::$instance) {
            self::$instance = new Self($registry);
        }
        return self::$instance;
    }

    public function registry() : ?ServiceRegistry
    {
        return $this->registry ?? null;
    }

    public function find(string $name): Service
    {
        return $this->registry->get($name);
    }
}
