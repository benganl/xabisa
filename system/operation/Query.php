<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\operation;

abstract class Query implements Operation
{
    abstract public function query(OperationContext $context): QueryResult;
}
