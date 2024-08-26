<?php

declare(strict_types= 1);

namespace wyzetech\shabisa\system\database;

class QueryParamProcessor {
    private $params = [];

    public function __construct(array $params) {
        $this->params = $params;
    }

    public function getParams(): array {
        return $this->params;
    }

    public function queryParams() {
        foreach ($this->params as $key => $value) {
            $queryParam = new QueryParam($key, $value);
            $queryParam->set
        }
    }
}