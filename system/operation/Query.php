<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\operation;

abstract class Query
{
    abstract public function query(): QueryResult;
}
