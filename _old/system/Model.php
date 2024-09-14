<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;

class Model
{
    private ?array $data;

    public function __construct()
    {
        $this->data = [];
    }

    public function __set($name, $value)
    {
        $this->data[$name] = $value;
    }

    public function __get($name)
    {
        return $this->data[$name];
    }
}
