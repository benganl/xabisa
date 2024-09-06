<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 5:09 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\service;

use wyzetech\shabisa\system\Service;

class ServiceProvider
{
    private static ?ServiceProvider $instance = null;

    private $services = [];

    private function __construct()
    {
        // hidden to prevent direct instantiation.
    }

    public static function getInstance(): ServiceProvider
    {
        if (self::$instance === null) {
            self::$instance = new self();
        }
        return self::$instance;
    }

    public function find(string $serviceName): ?Service
    {
        $name = trim($serviceName);
        return $this->services[trim($name)] ?? null;
    }

    public function register(string $serviceName, Service $service): void {
        $name = trim($serviceName);
        if (array_key_exists($name, $this->services) === false) {
            $this->services[$name] = $service;
        }
    }
}
