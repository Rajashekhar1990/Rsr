
package com.example.demo;

import java.io.File;
import java.util.Iterator;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.AbortedByHookException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class CommitandPush {

	public static void main(String[] args) {
		
	
    String name = "RamaRajuUppalapati";
    String password = "ramaraju549";
    String url = "https://github.com/RamaRajuUppalapati/CommitandPush.git";

    CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name, password);
    // clone
    File dir = new File("/abc");
		 CloneCommand cc = new CloneCommand() .setCredentialsProvider(cp)
		 .setDirectory(dir) .setURI(url);
	
    Git git = null;
	try {
		git = cc.call();
	} catch (GitAPIException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    // add
    AddCommand ac = git.add();
    ac.addFilepattern(".");
    try {
		ac.call();
	} catch (GitAPIException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // commit
    CommitCommand commit = git.commit();
    commit.setCommitter("RamaRajuUppalapati", "ramaraju549@gmail.com")
            .setMessage("push war");
    try {
		commit.call();
	} catch (UnmergedPathsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (AbortedByHookException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (GitAPIException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // push
    PushCommand pc = git.push();
    pc.setCredentialsProvider(cp)
            .setForce(true)
            .setPushAll();
    Iterator<PushResult> it=null;
	try {
		it = pc.call().iterator();
	} catch (TransportException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (GitAPIException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(it.hasNext()){
	    System.out.println(it.next().toString());
	}

    // cleanup
    dir.deleteOnExit();
}

}