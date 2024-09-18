<?php

declare(strict_types=1);

namespace xabisa\app\core\controller;
use xabisa\sys\Controller;
use xabisa\sys\Request;

class IndexController extends Controller
{
    public function index(): void
    {
        echo '<pre>';
        var_dump($this->getRequest());
        echo '</pre>';
        // echo '<br />Hello from controller!!!<br />';
        $this->render("index");
    }

    public function greet(): void
    {
        // echo '<br />Greeting from controller!!!<br />';
        $this->render("greet");
    }
}
