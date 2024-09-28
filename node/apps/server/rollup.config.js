import commonjs from "@rollup/plugin-commonjs";
import typescript from "@rollup/plugin-typescript";
import { nodeResolve } from "@rollup/plugin-node-resolve";
import json from "@rollup/plugin-json";
import run from "@rollup/plugin-run";

const isDev = process.env.ROLLUP_WATCH === "true";

export default {
  input: "src/index.ts",
  output: {
    dir: "dist",
    format: "es",
    sourcemap: true,
    preserveModules: true,
    preserveModulesRoot: "src",
  },
  plugins: [
    typescript({
      tsconfig: "tsconfig.json",
    }),
    nodeResolve({
      preferBuiltins: true,
    }),
    commonjs(),
    json(),
    isDev &&
      run({
        execArgv: ["--inspect", "-r", "source-map-support/register"],
      }),
  ],
  external: [/node_modules/],
};
