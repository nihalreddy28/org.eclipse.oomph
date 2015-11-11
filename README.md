org.eclipse.oomph
=================

 [TravisCI ![Build Status](https://travis-ci.org/peterkir/org.eclipse.oomph.png)](https://travis-ci.org/peterkir/org.eclipse.oomph)

# CI build results are available here 

http://peterkir.github.io/org.eclipse.oomph/

# Purpose 
- Mirrored and adapted from https://git.eclipse.org/r/oomph/org.eclipse.oomph
- Use custom configurations
- Make oomph independent of eclipse.org site

### What is changed wrt orginal

- adding custom "ResourceExtract Task" capable of extracting zip archive uri's during installation
- create additional system property (oomph.p2.agent.path) for p2 AgentManagerImpl
- use custom product and project configurations from https://github.com/peterkir/idefix

# Links

- [Eclipse Oomph Authoring](https://wiki.eclipse.org/Eclipse_Oomph_Authoring)
- [Forum]()http://www.eclipse.org/forums/eclipse.oomph
- [Eclipse Oomph FAQ](https://wiki.eclipse.org/Eclipse_Oomph_FAQ)
