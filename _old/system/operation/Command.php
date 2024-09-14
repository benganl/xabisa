<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\operation;
use wyzetech\shabisa\system\context\ExecutionContext;

abstract class Command implements Operation
{
    abstract public function execute(ExecutionContext $context): CommandResult;
}
