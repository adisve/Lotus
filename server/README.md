# Backend for Lotus

This is the backend for Lotus, a web application for educating your employees.

## Setup

The server runs off of fast-api. It is a python framework that is built on top of starlette and uvicorn. It is a very fast and easy to use framework. It is also very easy to deploy.

Versioning is done through asdf. This is a version manager that allows you to install multiple versions of a language and switch between them. This is useful for python because you can have multiple versions of python installed and switch between them. This is useful for when you are working on multiple projects that require different versions of python.

For virtual environments we use pipenv. This is a virtual environments manager that allows you to create a virtual environments for your project. This is useful for when you are working on multiple projects that require different versions of python packages.

- Install [asdf](https://asdf-vm.com/#/core-manage-asdf-vm)
- Install the python plugin for asdf:

    ```zsh
    asdf plugin add python
    ```

- Install the python version specified in the `.tool-versions` file:

    ```zsh
    asdf install
    ```

- Install [pipenv](https://pipenv.pypa.io/en/latest/install/#installing-pipenv)
- Activate the virtual environments:

    ```zsh
    pipenv shell
    ```

- Install the python packages:

    ```zsh
    pipenv install
    ```

- Run the server:

    ```zsh
    uvicorn main:app --reload
    ```

You're all set! The server should be running on `localhost:8000`

### Important Endpoints

Fast-api has a built in swagger ui that allows you to test the api. You can access it at `localhost:8000/docs`

## If you have dependencies installed already

If you have python and pipenv installed already, you can just run:

```zsh
pipenv shell
pipenv install
uvicorn main:app --reload
```

And then go to `localhost:8000/docs` to see the swagger ui.
