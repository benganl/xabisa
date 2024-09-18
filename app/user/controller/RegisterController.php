<?php

declare(strict_types=1);

namespace xabisa\app\user\controller;
use xabisa\sys\Controller;
use xabisa\sys\Request;

class RegisterController extends Controller
{
    public function index(Request $request): void
    {
        if (!$request->ispost) {
            $this->render("user/register");
        } else {
            $this->handleRegistration($request);
        }
    }

    private function handleRegistration(Request $request): void
    {
        var_dump($request);
        echo "Handling registration";
    }
}
