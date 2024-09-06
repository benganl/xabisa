<?php

namespace wyzetech\shabisa\system;

use Jenssegers\Blade\Blade;

class View
{
    private ?Blade $blade = null;

    public function __construct()
    {
        $this->blade = new Blade(__VIEW_DIR__, __VIEW_CACHE_DIR__);
    }

    public function render(string $viewName, array $data = []): void
    {
        echo $this->blade->render($viewName, $data);
    }
}
