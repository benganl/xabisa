<?php

declare(strict_types=1);

namespace xabisa\sys;

abstract class Controller
{
    private View $view;
    protected ?Request $request;
    
    public function setRequest(Request $request): void
    {
        $this->request = $request;
    }

    public function getRequest(): ?Request
    {
        return $this->request;
    }

    public function setView(View $view): void
    {
        $this->view = $view;
    }

    public function getMethod(): ?string
    {
        return $this->request->method ?? null;
    }

    protected function render(string $viewName, array $data = []): void
    {
        $this->view->render($viewName, $data);
    }
}
