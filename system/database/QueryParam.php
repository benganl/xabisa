<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system\database;

class QueryParam
{
    private string $name;
    private mixed $value;
    private bool $boundValue;

    public function __construct(string $name, mixed $value, bool $boundValue = true)
    {
        $this->name = $name;
        $this->value = $value;
        $this->boundValue = $boundValue;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function getValue(): mixed
    {
        return $this->value;
    }

    public function isBoundValue(): bool
    {
        return $this->boundValue;
    }

    public function setBoundValue(bool $boundValue): void
    {
        $this->boundValue = $boundValue;
    }
}
