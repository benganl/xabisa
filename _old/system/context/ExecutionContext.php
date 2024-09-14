<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\context;

use wyzetech\shabisa\system\Configuration;
use wyzetech\shabisa\system\Database;
use wyzetech\shabisa\system\Service;
use wyzetech\shabisa\system\service\ServiceRegistry;

class ExecutionContext
{
    private ?ServiceRegistry $serviceRegistry;

    private array $request;

    private Configuration $config;

    public function __construct(array $request, ServiceRegistry $serviceRegistry, Configuration $config)
    {
        $this->serviceRegistry = $serviceRegistry;
        $this->request = $request;
        $this->config = $config;
    }

    public function setRequest(array $request): void
    {
        $this->request = $request;
    }

    public function setServiceRegistry(ServiceRegistry $serviceRegistry): void
    {
        $this->serviceRegistry = $serviceRegistry;
    }

    public function setConfig(Configuration $config): void
    {
        $this->config = $config;
    }

    public function getConfig(): Configuration
    {
        return $this->config;
    }

    public function get(string $serviceName): Service
    {
        return $this->serviceRegistry->get($serviceName);
    }

    public function database(): Database
    {
        return Database::getInstance($this->config);
    }

    public function insert(string $sql, ?array $params = null): int
    {
        return Database::getInstance($this->config)->insert($sql, $params);
    }

    public function select(string $sql, ?array $params = null): array
    {
        return Database::getInstance($this->config)->select($sql, $params);
    }

    public function update(string $sql, ?array $params): int
    {
        return Database::getInstance($this->config)->update($sql, $params);
    }

    public function delete(string $sql, ?array $params): int
    {
        return Database::getInstance($this->config)->delete($sql, $params);
    }

    public function beginTransaction(): bool
    {
        return Database::getInstance($this->config)->beginTransaction();
    }

    public function commit(): bool
    {
        return Database::getInstance($this->config)->commit();
    }

    public function rollBack(): bool
    {
        return Database::getInstance($this->config)->rollBack();
    }
}
