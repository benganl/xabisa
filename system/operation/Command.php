<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\operation;

abstract class Command implements Operation
{
    abstract public function execute(OperationContext $context): CommandResult;
}
