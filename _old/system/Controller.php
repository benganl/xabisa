<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;
use wyzetech\shabisa\system\context\ExecutionContext;
use wyzetech\shabisa\system\operation\Command;
use wyzetech\shabisa\system\operation\CommandResult;
use wyzetech\shabisa\system\operation\Query;
use wyzetech\shabisa\system\operation\QueryResult;

abstract class Controller
{
    private ?View $view;

    private ExecutionContext $executionContext;

    public function __construct()
    {
        $this->view = new View();
    }

    public function setExecutionContext(ExecutionContext $executionContext): void
    {
        $this->executionContext = $executionContext;
    }

    protected function render(string $viewName, array $data = []): void
    {
        $this->view->render($viewName, $data);
    }

    protected function get(string $serviceName): Service
    {
        return $this->executionContext->get($serviceName);
    }

    protected function query(Query $query): QueryResult
    {
        try {
            return $query->getResult($this->executionContext);
        } catch (\Exception $e) {
            throw new \RuntimeException('Query execution failed: ' . $e->getMessage());
        }
    }

    protected function execute(Command $command): CommandResult
    {
        try {
            return $command->execute($this->executionContext);
        } catch (\Exception $e) {
            throw new \RuntimeException('Command execution failed: ' . $e->getMessage());
        }
    }
}
