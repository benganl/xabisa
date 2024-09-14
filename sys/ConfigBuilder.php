<?php

declare(strict_types=1);

namespace xabisa\sys;

class ConfigBuilder
{
    private static ?ConfigBuilder $instance = null;

    private function __construct()
    {
        // No need to instantiate this
    }

    public static function getInstance(): ConfigBuilder
    {
        if (self::$instance === null) {
            self::$instance = new self();
        }
        return self::$instance;
    }

    public function build() : Config
    {
        return new Config();
    }
}