require "rake/clean"

SCALA_HOME = "/usr/local/Cellar/scala/2.7.7"

# Should be pretty generic below here

SRC = FileList["src/**/*.scala"]
CLASSES = SRC.map {|f| f.pathmap("%{src,bin}X.class")}
SPEC_SRC = FileList["spec/src/**/*.scala"]
SPEC_CLASSES = SPEC_SRC.map {|f| f.pathmap("%{src,bin}X.class")}
CLEAN.include %w(bin spec/bin)

desc "Compile and run specs"
task :default => :spec

desc "Run specs"
task :spec => CLASSES + SPEC_CLASSES do
  sh "scala -cp #{SCALA_HOME}/lib/scalatest-1.0.jar:bin:spec/bin org.scalatest.tools.Runner -o -p spec/bin"
end

directory "bin"
directory "spec/bin"

rule(%r(^bin/.*\.class) => [proc {|f| f.pathmap("%{^bin,src}X.scala")}, "bin"]) do |t|
  command = "fsc -deprecation -classpath bin -sourcepath src -d bin #{t.source}"
  sh(command) || fail("Compilation failed")
end

rule(%r(^spec/bin/.*\.class) => [proc {|f| f.pathmap("%{^spec/bin,spec/src}X.scala")}, "spec/bin"] + CLASSES) do |t|
  command = "fsc -deprecation -classpath bin -sourcepath spec/src -d spec/bin #{t.source}"
  sh(command) || fail("Spec compilation failed")
end
