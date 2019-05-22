job('NodeJS Docker example') {
    scm {
        git('https://github.com/yhh0/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('yhh0')
            node / gitConfigEmail('yhh0@hotmail.com')
        }
    }
    triggers {
        scm('* * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('docker4yh/nodejs_example')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
