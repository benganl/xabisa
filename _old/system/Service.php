<?php
/**
 * User: lanton
 * Date: 2024/08/25
 * Time: 4:56 PM
 */

declare(strict_types=1);

namespace wyzetech\shabisa\system;
use wyzetech\shabisa\system\context\ExecutionContext;

abstract class Service
{
    abstract public function execute(ExecutionContext $executionContext);

}
