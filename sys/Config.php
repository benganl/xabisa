<?php

declare(strict_types=1);

namespace xabisa\sys;
use Dotenv\Dotenv;

final class Config
{
    private array $data;
    private static ?self $instance = null;

    public static function getInstance(): self
    {
        if (self::$instance === null) {
            self::$instance = new self();
        }
        return self::$instance;
    }

    private function __construct()
    {
        Dotenv::createImmutable(__CONFIG_DIR__)->load();
        $this->data = [];
        $this->init();
    }

    public function __set($name, $value): void
    {
        $name = strtolower(trim($name));
        $this->data[$name] = $value;
    }

    public function __get($name): ?string
    {
        $name = strtolower(trim($name));
        return $this->data[$name] ?? null;
    }

    public function get(string $key): ?string
    {
        $key = strtolower(trim($key));
        return $this->data[$key] ?? null;
    }

    public function set(string $key, string $value): void
    {
        $key = strtolower(trim($key));
        $this->data[$key] = $value;
    }

    public function init(): void
    {
        foreach ($_ENV as $key => $value) {
            $this->set($key, $value);
        }
    }
}
