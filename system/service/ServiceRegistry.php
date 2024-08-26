<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 5:30 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\service;

use wyzetech\shabisa\system\Service;

class ServiceRegistry
{
    private $services = [];
    private static ?ServiceRegistry $instance = null;

    public static function getInstance(): ServiceRegistry
    {
        if (self::$instance === null) {
            self::$instance = new ServiceRegistry();
        }
        return self::$instance;
    }

    public function register(string $name, Service $service): void
    {
        $name = trim($name);
        if (array_key_exists($name, $this->services) === false) {
            $this->services[$name] = $service;
        }
    }

    public function get(string $name): ?Service
    {
        return $this->services[trim($name)] ?? null;
    }
}
