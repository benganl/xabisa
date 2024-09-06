<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\context;

use wyzetech\shabisa\system\operation\Command;
use wyzetech\shabisa\system\operation\CommandResult;
use wyzetech\shabisa\system\operation\OperationContext;
use wyzetech\shabisa\system\operation\Query;
use wyzetech\shabisa\system\operation\QueryResult;
use wyzetech\shabisa\system\Service;
use wyzetech\shabisa\system\service\ServiceProvider;

class ExecutionContext implements OperationContext
{
    private ?ServiceProvider $serviceProvider = null;

    public function setServiceLocator(ServiceProvider $serviceProvider)
    {
        $this->serviceProvider = $serviceProvider;
    }

    public function query(Query $query): QueryResult
    {
        try {
            return $query->query($this);
        } catch (\Exception $e) {
            throw new \RuntimeException('Query execution failed: ' . $e->getMessage());
        }
    }

    public function execute(Command $command): CommandResult
    {
        try {
            return $command->execute($this);
        } catch (\Exception $e) {
            throw new \RuntimeException('Command execution failed: ' . $e->getMessage());
        }
    }

    public function find(string $serviceName): Service
    {
        $name = trim($serviceName);
        return $this->serviceProvider->find($name);
    }
}
