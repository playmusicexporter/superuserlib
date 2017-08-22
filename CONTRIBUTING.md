## Contributing to this project.

Thanks for considering to contribute to this. There are a few things you'll want to look out for, 
to make sure that your contributions are merged back though:

1. Ask, before you actually implement something: You have a great idea, and are eager to implement it, but if it turns out, 
this is not something we (the maintainers) want to merge, it wasn't really worth the effort. 
Sure, you can still use it (or publish it somewhere, this is the beauty of Open Source), but it won't make it in here.
To avoid situations like this, 
just open an issue here (or ask in [#playmusicexporter:jcg.re](https://matrix.to/#/#playmusicexporter:jcg.re))
and discuss it with us before you start working on it. Once you have started,
put up a PR (you don't need to wait until you have something finished), 
marked with [WIP] in the beginning, until you think it's finished, and ready to be merged back.

2. License: By submitting a PR, you accept that the code you send us will be licensed under the license available in the LICENSE file
in the repository root.

3. Commit style: We generate our Changelog automatically with [clog](https://github.com/clog-tool/clog-cli), 
based on the commit messages. To get this working, please pay close attention to [this guide here](https://github.com/conventional-changelog/conventional-changelog/blob/a5505865ff3dd710cf757f50530e73ef0ca641da/conventions/angular.md).

4. Branching: We are trying to do branching with git flow, 
and most times it does work (sometimes we have commits where they don't belong, but that is not intentional).
So please, send a PR to the develop branch when working on a new feature, and to those with commit access,
create new branches for new features, and then send PRs to develop too.
