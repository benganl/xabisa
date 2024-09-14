<?php

declare(strict_types=1);

namespace wyzetech\shabisa\application\core\controller;

use wyzetech\shabisa\system\Controller;

class IndexController extends Controller
{
    public function index(array $data = [])
    {
        return $this->render("index");
    }
}
