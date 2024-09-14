<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 5:09 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system\service;

use wyzetech\shabisa\system\Configuration;
use wyzetech\shabisa\system\Service;

class ServiceRegistry
{
    private static ?ServiceRegistry $instance = null;

    private $services = [];

    private function __construct()
    {
        // hidden to prevent direct instantiation.
    }

    public static function getInstance(array $request = [], Configuration $config): ServiceRegistry
    {
        if (self::$instance === null) {
            self::$instance = new self();
        }
        return self::$instance;
    }

    public function get(string $serviceName): ?Service
    {
        $name = trim($serviceName);
        return $this->services[$name] ?? null;
    }

    public function register(string $serviceName, Service $service): void {
        $name = trim($serviceName);
        if (array_key_exists($name, $this->services) === false) {
            $this->services[$name] = $service;
        }
    }
}
