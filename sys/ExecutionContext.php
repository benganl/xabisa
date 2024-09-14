<?php

declare(strict_types= 1);

namespace xabisa\sys;

class ExecutionContext
{
    private Config $config;

    public function __construct(Config &$config)
    {
        $this->config = $config;
    }
}
