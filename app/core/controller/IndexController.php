<?php

declare(strict_types=1);

namespace xabisa\app\core\controller;
use xabisa\sys\Controller;
use xabisa\sys\Request;

class IndexController extends Controller
{
    public function index(?Request $request)
    {
        echo 'Hello from controller!!!';
    }

    public function greet(Request $request): void
    {
        echo '<pre>';
        var_dump($request);
        echo '</pre>';
        echo 'Greeting from controller!!!';
    }
}
