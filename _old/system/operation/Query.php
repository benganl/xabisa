<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\operation;
use wyzetech\shabisa\system\context\ExecutionContext;

abstract class Query implements Operation
{
    abstract public function getResult(ExecutionContext $context): QueryResult;
}
