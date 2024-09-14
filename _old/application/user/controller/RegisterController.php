<?php

declare(strict_types=1);

namespace wyzetech\shabisa\application\user\controller;
use wyzetech\shabisa\system\Controller;

class RegisterController extends Controller
{
    public function index($data)
    {
        if ($data["isPost"]) {
            $this->render("user/signin");
        } else {
            $this->render("user/register");
        }
    }
}
