<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\context;
use wyzetech\shabisa\system\operation\Command;
use wyzetech\shabisa\system\operation\CommandResult;
use wyzetech\shabisa\system\operation\OperationContext;
use wyzetech\shabisa\system\operation\Query;
use wyzetech\shabisa\system\operation\QueryResult;

class ExecutionContext implements OperationContext
{
    public function query(Query $query): QueryResult
    {
        $query->query($this);
    }

    public function execute(Command $command): CommandResult
    {
        return $command->execute($this);
    }
}
