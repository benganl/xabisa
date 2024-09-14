<?php

declare(strict_types=1);

namespace wyzetech\shabisa\system;

class Configuration
{
    private static ?Configuration $instance = null;

    private ?array $data = null;

    private function __construct()
    {
        $this->data = [];
    }

    public static function getInstance(array $request = []): Configuration
    {
        if (self::$instance === null) {
            self::$instance = new self();
            self::$instance->init();
        }
        return self::$instance;
    }

    public function __set(string $name, $value): void
    {
        if (!array_key_exists($name, $this->data)) {
            $this->data[$name] = $value;
            return;
        }
        throw new \RuntimeException("Key already set!");
    }

    public function __get(string $name)
    {
        if (array_key_exists($name, $this->data)) {
            return $this->data[$name];
        }
        throw new \InvalidArgumentException("Configuration key not found!");
    }

    public function init(): void
    {
        $dbc = [];
        $dbc['username'] = "dev";
        $dbc['password'] = "dev@c00l!";
        $dbc['dbname'] = "wyzecmsdb";
        $dbc['host'] = "localhost";
        $dbc['port'] = "3306";
        $dbc['driver'] = "mysql";
        
        Configuration::getInstance()->db = $dbc;
    }
}
