<?php

declare(strict_types=1);

namespace xabisa\utils;
use Symfony\Component\Uid\Uuid;

final class Guid
{
    public static function generate(): string
    {
        return Uuid::v7()->toString();
    }

    public static function isValid(string $uuid): bool
    {
        return Uuid::isValid($uuid);
    }

    public static function fromString(string $uuid): Uuid
    {
        return Uuid::fromString($uuid);
    }
}
