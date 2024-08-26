<?php

declare(strict_types= 1);

namespace wyzetech\shabisa\system\database;

class QueryParam {
    private string $name;
    private mixed $value;
    private bool $boundValue = false;

    public function __construct(string $name, mixed $value, bool $boundValue = false) {
        $this->name = $name;
        $this->value = $value;
        $this->boundValue = $boundValue;
    }

    public function getName(): string {
        return $this->name;
    }

    public function getValue(): mixed {
        return $this->value;
    }

    public function getBoundValue(): bool {
        return $this->boundValue;
    }
}
