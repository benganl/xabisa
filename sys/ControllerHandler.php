<?php

declare(strict_types=1);

namespace xabisa\sys;

class ControllerHandler
{
    private ExecutionContext $context;

    public function __construct(ExecutionContext $context)
    {
        $this->context = $context;
    }

    public function handle(Controller $controllerObj): void
    {
        echo '<pre> Execute Start </pre>';
        $method = $controllerObj->getMethod();
        $request = $controllerObj->getRequest();
        call_user_func([$controllerObj, $method], $request);
        echo '<pre> Execute End </pre>';
    }
}
