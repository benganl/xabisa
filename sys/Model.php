<?php

declare(strict_types=1);

namespace xabisa\sys;

abstract class Model
{
    protected ?string $id;

    public function getId(): ?string
    {
        return $this->id;
    }
}
