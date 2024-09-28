import { z } from "zod";

export const ConditionalOperators = z
  .union([
    z.literal("GTE").describe("Greater than or equal"),
    z.literal("GT").describe("Greater than"),
    z.literal("LT").describe("Less than"),
    z.literal("LTE").describe("Less than or equal"),
    z.literal("EQ").describe("Equal"),
    z.literal("NE").describe("Not equal"),
    z.literal("EXIST").describe("Match fields with a value"),
    z.literal("MATCH").describe("Match a regular expression"),
  ])
  .describe("Conditional operator");

export type ConditionalOperators = z.infer<typeof ConditionalOperators>;

export const LogicalOperator = z
  .union([
    z.literal("AND").describe("Logical AND"),
    z.literal("OR").describe("Logical OR"),
    z.literal("NOT").describe("Logical NOT"),
  ])
  .describe("Logical operator");

export type LogicalOperator = z.infer<typeof LogicalOperator>;

export const FilterItem = z
  .object({
    field: z.string().describe("Field to filter"),
    operator: ConditionalOperators,
    value: z.string().describe("Value to filter"),
  })
  .describe(
    "Filter element that is based on a field, executed by the operator, and filtered by the value"
  );

export type FilterItem = z.infer<typeof FilterItem>;

const BaseFilter = z.object({
  operator: LogicalOperator,
  filter: FilterItem,
});

export type Filters = z.infer<typeof BaseFilter> & {
  filters?: Filters[];
};

export const Filters: z.ZodType<Filters> = BaseFilter.extend({
  filters: z.array(BaseFilter).optional().describe("Nested filter groups"),
});
