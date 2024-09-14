<?php

declare(strict_types=1);

namespace xabisa\sys;

class Request
{
    private array $data;

    public function __construct() {
        $this->data = [];
    }

    public function __set($name, $value) {
        $name = strtolower(trim($name));
        $this->data[$name] = $value;
    }

    public function __get($name) {
        $name = strtolower(trim($name));
        return $this->data[$name] ?? null;
    }
}
