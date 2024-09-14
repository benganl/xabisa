<?php

declare(strict_types=1);

namespace xabisa\sys;

class ControllerHandler
{
    private ExecutionContext $context;
    private Request $request;

    public function __construct(ExecutionContext $context, Request $request)
    {
        $this->context = $context;
        $this->request = $request;
    }

    public function handle(Controller $controllerObj): void
    {
        $method = $this->request->method;
        
        call_user_func([$controllerObj, $method], $this->request);
    }
}
