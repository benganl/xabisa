<?php

declare(strict_types=1);

namespace xabisa\sys;
use Jenssegers\Blade\Blade;


final class View
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
