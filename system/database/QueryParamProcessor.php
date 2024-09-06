<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\database;

use \PDO;
use \PDOStatement;

class QueryParamProcessor
{
    private array $params;

    public function __construct(array $params)
    {
        $this->params = $params;
    }

    public function bindParams(PDOStatement $statement): void
    {
        foreach ($this->params as $queryParam) {
            $name = $queryParam->getName();
            $value = $queryParam->getValue();
            $bound = $queryParam->isBoundValue();

            if ($bound) {
                $statement->bindParam($name, $value, $this->getParamType($value));
            } else {
                $statement->bindValue($name, $value, $this->getParamType($value));
            }
        }
    }

    private function getParamType(mixed $value): int
    {
        if (is_int($value)) {
            return PDO::PARAM_INT;
        } elseif (is_bool($value)) {
            return PDO::PARAM_BOOL;
        } elseif (is_null($value)) {
            return PDO::PARAM_NULL;
        } else {
            return PDO::PARAM_STR;
        }
    }
}